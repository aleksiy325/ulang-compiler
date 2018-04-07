.source ar2.ir
.class public ar2
.super java/lang/Object

.method public static __main()V
	.limit locals 14
	.var 0 is T0  [I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.var 8 is T8  I from L_0 to L_1
	.var 9 is T9  I from L_0 to L_1
	.var 10 is T10  Z from L_0 to L_1
	.var 11 is T11  I from L_0 to L_1
	.var 12 is T12  I from L_0 to L_1
	.var 13 is T13  I from L_0 to L_1
	.limit stack 16
L_0:
	aconst_null 
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	ldc 0
	istore 6
	ldc 0
	istore 7
	ldc 0
	istore 8
	ldc 0
	istore 9
	ldc 0
	istore 10
	ldc 0
	istore 11
	ldc 0
	istore 12
	ldc 0
	istore 13
.line 18
;		T0 := NEWARRAY I3;
	ldc 3
	newarray int
	astore 0
.line 19
;		T2 := 1;
	ldc 1
	istore 2
.line 20
;		T3 := 0;
	ldc 0
	istore 3
.line 21
;		T0[T3] := T2;
	aload 0
	iload 3
	iload 2
	iastore
.line 22
;		T4 := 2;
	ldc 2
	istore 4
.line 23
;		T5 := 1;
	ldc 1
	istore 5
.line 24
;		T0[T5] := T4;
	aload 0
	iload 5
	iload 4
	iastore
.line 25
;		T6 := 3;
	ldc 3
	istore 6
.line 26
;		T7 := 2;
	ldc 2
	istore 7
.line 27
;		T0[T7] := T6;
	aload 0
	iload 7
	iload 6
	iastore
.line 28
;		T8 := 0;
	ldc 0
	istore 8
.line 29
;		T1 := T8;
	iload 8
	istore 1
.line 30
;		L0:;
L0:
.line 31
;		T9 := 3;
	ldc 3
	istore 9
.line 32
;		T10 := T1 I< T9;
	iload 1
	iload 9
	isub
	iflt L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 10
.line 33
;		T10 := Z! T10;
	iload 10
	ldc 1
	ixor
	istore 10
.line 34
;		IF T10 GOTO L1;
	iload 10
	ifne L1
.line 35
;		T11 := T0[T1];
	aload 0
	iload 1
	iaload
	istore 11
.line 36
;		PRINTLNI T11;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 11
	invokevirtual java/io/PrintStream/println(I)V
.line 37
;		T12 := 1;
	ldc 1
	istore 12
.line 38
;		T13 := T1 I+ T12;
	iload 1
	iload 12
	iadd
	istore 13
.line 39
;		T1 := T13;
	iload 13
	istore 1
.line 40
;		GOTO L0;
	goto L0
.line 41
;		L1:;
L1:
.line 42
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
	invokestatic ar2/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
