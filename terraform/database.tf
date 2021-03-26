module "rds" {
  source  = "terraform-aws-modules/rds/aws"
  version = "2.20.0"

  identifier = "mdcode-beerstore-rds"

  engine = "postgres"
  engine_version = "12.5"
  instance_class = "db.t2.micro"
  allocated_storage = "100"
  major_engine_version = "12"

  name = "beerstore"
  username = "beerstore"
  password = "beerstore"
  port = "5432"

  vpc_security_group_ids = ["${aws_security_group.database.id}"]

  maintenance_window = "Thu:03:30-Thu:05:30"
  backup_window = "05:30-06:30"
  storage_type = "gp2"
  multi_az = "true"
  family = "postgres10"

  create_db_parameter_group = false

  subnet_ids = "${flatten(chunklist(aws_subnet.private_subnet.*.id, 1))}"
}