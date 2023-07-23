.data

    prompt: .asciiz "Enter a sum:"
	rtn: .asciiz "The value is:"
	str: .space 200
.text

main:
   
	la $a0, prompt # Print the prompt string
	li $v0, 4 
	syscall
	
    li $v0,11 
    li $a0,10
    syscall
    
    li $s5, '+'
    
	# Getting the str
	
    li $v0, 8
	la $a0, str
	li $a1, 20
	syscall
	
	li $v0, 4 
	la $a0, rtn
	syscall
	
    li $v0,11  
    li $a0,10
    syscall
    
    la $s2, str 
    li $t2,0
    li $t4,0 
    li $s1,0 

lp:
    li $t4,0 
    j loop
    
loop:
    lb $t0, 0($s2)
    addi $s2, $s2, 1

    beq $t0, 10, exit	
    beq $t0, $s5, final
    
    mul $t4, $t4, 10    
    sub $t0, $t0, '0'    
    add $t4, $t4, $t0 
    j loop

final: 
    add $s1,$s1,$t4
    j lp

exit:
    add $s1,$s1,$t4
    li $v0, 1
	move $a0, $s1 
	syscall
    li $v0, 10
	syscall