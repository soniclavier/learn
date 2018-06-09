#!/bin/bash
aws ec2 disassociate-address --association-id eipassoc-5c2c5b63
aws ec2 release-address --allocation-id eipalloc-f8660dc5
aws ec2 terminate-instances --instance-ids i-015e364c15581eec1
aws ec2 wait instance-terminated --instance-ids i-015e364c15581eec1
aws ec2 delete-security-group --group-id sg-af5f6ed2
aws ec2 disassociate-route-table --association-id rtbassoc-f5948f8d
aws ec2 delete-route-table --route-table-id rtb-f01a6f89
aws ec2 detach-internet-gateway --internet-gateway-id igw-5e525439 --vpc-id vpc-4cc2f22a
aws ec2 delete-internet-gateway --internet-gateway-id igw-5e525439
aws ec2 delete-subnet --subnet-id subnet-73601a15
aws ec2 delete-vpc --vpc-id vpc-4cc2f22a
echo If you want to delete the key-pair, please do it manually.
