#!/bin/bash
aws ec2 disassociate-address --association-id eipassoc-b292868f
aws ec2 release-address --allocation-id eipalloc-4e2ecd73
aws ec2 terminate-instances --instance-ids i-07c9a599e6c7d3af2
aws ec2 wait instance-terminated --instance-ids i-07c9a599e6c7d3af2
aws ec2 delete-security-group --group-id sg-d104b9ab
aws ec2 disassociate-route-table --association-id rtbassoc-00615979
aws ec2 delete-route-table --route-table-id rtb-40dbfe26
aws ec2 detach-internet-gateway --internet-gateway-id igw-913ebef6 --vpc-id vpc-209f4e46
aws ec2 delete-internet-gateway --internet-gateway-id igw-913ebef6
aws ec2 delete-subnet --subnet-id subnet-794b4e30
aws ec2 delete-vpc --vpc-id vpc-209f4e46
echo If you want to delete the key-pair, please do it manually.
