#Makefile for Assignmet 1
#kevinc chiloane
#07 April 2021

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=BinaryTreeNode.class  BTQueueNode.class BTQueue.class BinaryTree.class AVLTree.class ReadFromFile.class Student.class AccessAVLApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

runAVL: $(CLASS_FILES)
	java -cp $(BINDIR) AccessAVLApp