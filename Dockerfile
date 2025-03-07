FROM bellsoft/liberica-openjdk-alpine:17.0.8

#workspace
WORKDIR /home/selenium-docker

#add required files to run the test (configs and such)
ADD target/docker-resources .

#environment variables (browser, hub host, thread count and etc...)



# running the test at startup
ENTRYPOINT java -cp 'libs/*' -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=${HUB_HOST} -Dbrowser=${BROWSER} org.testng.TestNG test-suites/${TEST_SUITE}





