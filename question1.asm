
.data
    prompt: .asciiz "Enter n, followed by n lines of text:"
	rtn: .asciiz "The values are:"
	space: .space 40
.text
main:
	li $a1, 8 # limiting reader to eight characters
	la $a0, prompt #printing the prompt string
	li $v0, 4 
	syscall
	
    li $v0,11  
    li $a0,10
    syscall
	
	li $v0, 5
	syscall
	la $s2, space
	move $s1, $v0 
	mul $t1, $s1, 4 
	move $s0, $gp   
	add $gp, $t1, $gp

    add $t4, $s0, $zero 
receive:      
    blt $s1,1,topd 
    sub $s1,$s1, 1

    li $v0, 8
	move $a0, $s2
	li $a1, 20
	
	syscall
	move $t2, $a0
	
	sw $t2, 0 ($t4)
    add $t4, $t4, 4
	add $s2,$s2,20

    j receive

reverse: # loop to print the line in reverse
    blt $t1,4,exit
    sub $t1,$t1, 4

    add $t6, $s0, $t1
	lw $t3, 0 ($t6)

    li $v0, 4
	move $a0, $t3
	syscall

    j reverse
	
exit:
    li $v0, 10
	syscall

topd:
    li $v0, 4 
	la $a0, rtn # # Printing the prompt string
	syscall

    li $v0,11  
    li $a0,10
    syscall

    j reverse
