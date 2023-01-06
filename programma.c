#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include<stdbool.h>

int   result =  1 ;bool   b =  true ;
int  fibonacci(  int  i ){
int   c2 =  2 ;
int   c1 =  1 ;
  if(   i== 0   ) {
 return  1 ;

}
 
 if(   i== 1   ) {
 return  1 ;

}
else {
int   r2, r1;
int   i2 =   i- c2 ;
int   i1 =   i- c1 ;
  r1=  fibonacci( i1);
 r2=  fibonacci( i2);
return   r1+ r2 ;

}
 

}





void  main(){
int   intero;
 printf("inserisci un numero per estrarre numero di fibonacci\n");
printf("scegli un numero positivo:");
scanf("%d",&intero);
 if(   intero <  0  ) {
 printf("numero non corretto\n");
return ;

}
else {
  result=  fibonacci( intero);
for( int  i =  0 ;  i <  5 ;  i++){
  if(  b ) {
 printf("numero di fibonacci: %d", result);
printf(" stampo b:%d\n", b);
 b= false ;

}
 

}
 
return ;

}
 

}


