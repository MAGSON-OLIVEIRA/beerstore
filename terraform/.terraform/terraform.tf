terraform {
  backend "s3" {
    bucket = "dev-aws-terraform-state-md"
    key = "beerstore-dev-aws"
    region = "us-east-1"
    profile = "terraform"
  }
}