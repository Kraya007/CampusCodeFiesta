
.data
    prompt: .asciiz "Enter n and formulae:"
	rtn: .asciiz "The values are:"
	input: .space 200
   
.text


main:
   	li $a1, 8		# Limiting read to 8 characters 
	la $a0, prompt 		# Printing the prompt string
	 li $v0, 4 
	syscall
    li $v0,11  
    li $a0,10
    syscall
    
	li $s5, '='
    li $s4,0
	li $v0, 5
	syscall
	la $s2, input 
	move $s1, $v0 
	add $s4,$s1,0
    add $t7,$s1,0
	mul $t1, $s1, 4 
    li $s7, 0 
	move $s0, $gp 
	add $gp, $t1, $gp
    add $t4, $s0, $zero 
    add $t6, $s0, 0
    add $t9, $s0, 0
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
vals:
    blt $s4,1, conca
    li $s3, 0    
    li $t1, 1 
	lw $t3, 0 ($t6) 
    add $s6,$t3,0 
    lb $t2, 0($s6)  
    beq $t2,$s5,lp
    add $t6, $t6, 4
    sub $s4,$s4,1
    j vals

final:
    add $s7,$s7,$s3 
    li $v0, 4
    move $a0, $t3
	syscall

    j vals
result:  

    lb $t0, 0($s6)  
    addi $s6, $s6, 1   
    beq $t0, 10, final   
    mul $s3, $s3, 10    
    sub $t0, $t0, '0'    
    add $s3, $s3, $t0     
    j result 
    
conca:
    blt $t7,1,exit
    li $s3, 0    
    li $t1, 1 
	lw $t3, 0 ($t9) 
    add $s6,$t3,0 
    add $t9, $t9, 4
    sub $t7,$t7,1
    j result
exit:
    li $v0, 1
	move $a0, $s7 
	syscall
    li $v0, 10
	syscall
lp:
    li $t1, 1   
    li $t2, 0   
    
    j number
number:  
    lb $t0, 1($s6)  
    addi $s6, $s6, 1   
    beq $t0, 10, close  
    mul $t2, $t2, 10    
    sub $t0, $t0, '0'
    add $t2, $t2, $t0     
    j number     
close:
    mul $t2,$t2,4
    add $t2, $s0,$t2
    lw $t3, 0 ($t2)
    sw $t3, 0($t6)
    add $t6, $t6, 4
    sub $s4,$s4,1
    j vals

topd:
    li $v0, 4 
	la $a0, rtn
	syscall
    li $v0,11  
    li $a0,10
    syscall
    j vals
