'''
Created on 31 May 2021

@artist: kndub

'''
import numpy
import matplotlib.pylab as plt
from cmath import pi, sqrt


def fd(a,b):
    if(a == 0 and b == pi/2):
        x = numpy.array([0,pi/6,pi/3,pi/2])
        y = numpy.array([0,1/2,(sqrt(3)/2),1])
        
        xplt = numpy.linspace(x[0],x[-1])
        yplt = numpy.array([],float)
        for x_a in xplt:
            y_b = 0
            for i,j in zip(x,y):
                y_b += j*numpy.prod((x_a-x[x != i])/(i-x[x!=i]))
            yplt = numpy.append(yplt,y_b)
        plt.plot(x,y,'ro',xplt,yplt,'b-')
        plt.xlim(0,pi/2)
        plt.ylim(0,1)
        plt.xlabel('x')
        plt.ylabel('y')
        plt.show()
        
def sd(a,b):
    if(a == 0 and b == 2*pi):
        x = numpy.array([0,pi/6,pi/3,pi/2,(2*pi)/3,(5*pi)/6,pi,(7*pi)/6,(4*pi)/3,(3*pi)/2,(5*pi)/3,(11*pi)/6,2*pi])
        y = numpy.array([0,1/2,(sqrt(3)/2),1,(sqrt(3)/2),1/2,0,-1/2,(-sqrt(3)/2),-1,(-sqrt(3)/2),-1/2,0])
        
        xplt = numpy.linspace(x[0],x[-1])
        yplt = numpy.array([],float)
        for x_a in xplt:
            y_b = 0
            for i,j in zip(x,y):
                y_b += j*numpy.prod((x_a-x[x != i])/(i-x[x!=i]))
            yplt = numpy.append(yplt,y_b)
        plt.plot(x,y,'ro',xplt,yplt,'b-')
        plt.xlim(0,2*pi)
        plt.ylim(-1,1)
        plt.xlabel('x')
        plt.ylabel('y')
        plt.show()
        
def dd(a,b):
    if(a == pi/2 and b == 2*pi):
        x = numpy.array([(2*pi)/3,(5*pi)/6,pi,(7*pi)/6,(4*pi)/3,(3*pi)/2,(5*pi)/3,(11*pi)/6,2*pi])
        y = numpy.array([-(sqrt(3)/2),-1/2,0,1/2,(sqrt(3)/2),1,(sqrt(3)/2),1/2,0])
        
        xplt = numpy.linspace(x[0],x[-1])
        yplt = numpy.array([],float)
        for x_a in xplt:
            y_b = 0
            for i,j in zip(x,y):
                y_b += j*numpy.prod((x_a-x[x != i])/(i-x[x!=i]))
            yplt = numpy.append(yplt,y_b)
        plt.plot(x,y,'ro',xplt,yplt,'b-')
        plt.xlim((2*pi)/3,2*pi)
        plt.ylim(-1,1)
        plt.xlabel('x')
        plt.ylabel('y')
        plt.show()

fd(0,pi/2)
sd(0,(2*pi))
dd(pi/2,2*pi)

            
        

        


    