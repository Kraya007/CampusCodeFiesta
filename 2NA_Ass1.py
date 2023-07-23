'''
Created on 08 May 2021

@author: kndub

'''
import numpy as np
'''
function f(x) to solve 
'''

def f(x):
    fx= x**3-10
    return fx


'''
first derivate of f(x)
'''
def f1(x):
    h = 0.0000001
    gx = (f(x + h) - f(x)) / h
    return gx

'''
second derivative of f(x)
'''
def f2(x):
    h = 0.0000001
    hx = (f1(x + h) - f1(x)) / h
    return hx
"""
The bisection method follows , in the interval [a,b] in between we attain
a root, assuming the exits only one root in the interval of [a,b] 
let c be a point in between the interval[a,b], if f(a)*f(b) > 0 there 
exist a root in the interval[c,b] a implies c and if f(a)*f(c) < 0 there
exist a root in the interval[a,c] b implies c
absolute(a -b) = 0 thus prefer tolerance to be zero 
"""
def bisectionMethod(a,b,t):
    itcount = 0
    while(np.abs(a-b)>=t):
        itcount += 1
        c = (a+b)/(2.0)
        signChange = f(a)*f(c) 
        if(signChange > t):
            a = c
        else:
            if(signChange < t):
                b = c

    return c ,itcount
answerB = bisectionMethod(2,4,1e-10)

def newtonMethod(g,t):
    itCount = 0
    while(np.abs(f(g))>=t):
        itCount+=1
        nextG = g-f(g)/f1(g)
        g = nextG
    return g, itCount
answerN = newtonMethod(2,1e-10)

def secant(a,b,t):
    itCount = 0
    if (f(a) * f(b) < 0):
        while True:
            itCount+=1
            eqn1 = (a * f(b) - b * f(a))
            eqn2 = (f(b) - f(a))
            eqn =eqn1/eqn2
            a = b
            b = eqn
            eqn_1 = (a * f(b) - b * f(a)) 
            eqn_2 = (f(b) - f(a))
            eqn_n = eqn_1/eqn_2
            if(abs(eqn_n - eqn) < t):
                break;
    return eqn,itCount

answerS = secant(2,4,1e-10) 
'''
question 2a solution: the implementation of the Halley's method 
'''
def HalleyMethod(a,t): 
    itCount = 0   
    while True:
        itCount +=1
        eqn = a - (2.0 * f(a) * f1(a)) / (2.0 * f1(a) * f1(a) - f(a)* f2(a))
        if abs(eqn - a) <= t:
            break
        a = eqn
    return eqn, itCount
answerH = HalleyMethod(2, 1e-10)
print("2b")
print("the root solution by Halley's method is: "+str(answerH[0])+" and the root solution by newton's method is: "+str(answerN[0]))
print("the number of iteration by Halley's method is: "+str(answerH[1])+" and the number of iteration by newton's method is: "+str(answerN[1]))
print("in conclusion the halley's method and newton's method are exact to each other")
print()
print("2c")
print("the root solution by Halley's method is: "+str(answerH[0])+" and the root solution by Bisection Method is: "+str(answerB[0]))
print("the number of iteration by Halley's method is: "+str(answerH[1])+" and the number of iteration by Biection method is: "+str(answerB[1]))
print("in conclusion the halley's method is more efficient than Bisection method")
print()
print("2d")
print("the root solution by Halley's method is: "+str(answerH[0])+" and the root solution by Secant Method is: "+str(answerS[0]))
print("the number of iteration by Halley's method is: "+str(answerH[1])+" and the number of iteration by Secant method is: "+str(answerS[1]))
print("in conclusion the halley's method is more efficient than Secant method")
print()

        
       




