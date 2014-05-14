APP      := clj-erl
VER      := 0.1.0
JAR_PATH := target/$(APP)-$(VER).jar

all: install

install:
	lein clean
	lein compile
	lein jar
	lein pom
	lein localrepo coords $(JAR_PATH)	
	lein localrepo install -p pom.xml $(JAR_PATH) $(APP) $(VER)
