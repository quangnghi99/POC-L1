sudo yum update –y
sudo yum install java-1.8.0 -y
sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum upgrade
sudo yum install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins

sudo cat /var/lib/jenkins/secrets/initialAdminPassword

sudo yum install maven -y
#maven home: /usr/share/maven
#java home: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.312.b07-1.amzn2.0.2.x86_64

sudo yum install docker -y
sudo service docker start
sudo usermod -a -G docker jenkins
reboot
