.source constants.ir
.class public constants
.super java/lang/Object

.method public static __main()V
	.limit locals 10
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  F from L_0 to L_1
	.var 2 is T2  Z from L_0 to L_1
	.var 3 is T3  Ljava/lang/String; from L_0 to L_1
	.var 4 is T4  C from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  F from L_0 to L_1
	.var 7 is T7  Z from L_0 to L_1
	.var 8 is T8  Ljava/lang/String; from L_0 to L_1
	.var 9 is T9  C from L_0 to L_1
	.limit stack 16
L_0:
	ldc 0
	istore 0
	ldc 0.0
	fstore 1
	ldc 0
	istore 2
	aconst_null 
	astore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	ldc 0.0
	fstore 6
	ldc 0
	istore 7
	aconst_null 
	astore 8
	ldc 0
	istore 9
.line 14
;		T5 := 5;
	ldc 5
	istore 5
.line 15
;		T0 := T5;
	iload 5
	istore 0
.line 16
;		T6 := 5.23;
	ldc 5.230000
	fstore 6
.line 17
;		T1 := T6;
	fload 6
	fstore 1
.line 18
;		T7 := FALSE;
	ldc 0
	istore 7
.line 19
;		T2 := T7;
	iload 7
	istore 2
.line 20
;		T8 := "test";
	ldc "test"
	astore 8
.line 21
;		T3 := T8;
	aload 8
	astore 3
.line 22
;		T9 := 'c';
	ldc 99
	istore 9
.line 23
;		T4 := T9;
	iload 9
	istore 4
.line 24
;		RETURN;
	return
L_1:
.end method

;--------------------------------------------;
;                                            ;
; Boilerplate                                ;
;                                            ;
;--------------------------------------------;

.method public static main([Ljava/lang/String;)V
	; set limits used by this method
	.limit locals 1
	.limit stack 4
	invokestatic constants/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
