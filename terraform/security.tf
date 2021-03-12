resource "aws_security_group" "allow_ssh" {
  vpc_id = aws_vpc.main.id
  name = "mdcode_allow_ssh"

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["179.179.59.22/32"]
  }
}

resource "aws_security_group" "database" {
  vpc_id = aws_vpc.main.id
  name = "mdcode_database"

  ingress {
    from_port = 5432
    protocol = "tcp"
    to_port = 5432
    self = true
  }

}