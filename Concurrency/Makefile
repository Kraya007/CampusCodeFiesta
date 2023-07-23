

JAVAC=/usr/bin/javac

.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES=ParallelMedianFilter.class 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

clean-tests:
	rm data/*.out

parallel: $(CLASS_FILES)
	java -cp $(BINDIR) ParallelMedianFilter $(inputFileName) $(filterSize) $(outputFileName)

java-docs: $(CLASS_FILES)
	javadoc src/*.java 
	mv jquery docs ; mv *l docs ; mv *s docs ; mv *t docs ; mv *p docs