--db creation
create database jkh01;
--creation of houses table
create table houses(
	id serial,
	street varchar(250),
	number int,
	entrances int,
	levels int,
	apartmentsByLevel int,
	primary key (id)
);
--creation of apartments table
create table apartments(
	id serial,
	number int,
	entrance int,
	level int,
	house_id int,
	area real,
	howManyTenants int,
	howManyRooms int,
	primary key (id),
	foreign key (house_id) references houses(id)
);
--creation of tenants table
create table tenants(
	id serial,
	name varchar(250),
	lastname varchar(250),
	primary key (id)
);
--adding column tenant_id to apartments table
alter table apartments add column tenant_id int;
--adding constraint  
ALTER TABLE apartments 
ADD CONSTRAINT apartments_tenants_id_fkey FOREIGN KEY (tenant_id)
      REFERENCES tenants (id) 
      ON UPDATE CASCADE ON DELETE CASCADE
