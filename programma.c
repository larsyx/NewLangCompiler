#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>




void  passaNumero(  int*numero){
int   numero2;
  *numero= 5 ;
printf("stampo da passaNumero:%d\n", *numero);
 *numero=  passaNumero2( *numero,& numero2);
printf("stampo numero 2: %d\n", numero2);
return ;

}


int  passaNumero2(  int num, int*num2){
  num= 10 ;
 *num2= 5 ;
return  num;

}



void main (){
char stringa[1000] ={  "molto bene"  };int   numero =  0 ;
 printf("esempio per controllare funzionamento parametri OUT funzione\n");
  passaNumero(& numero); 
printf("%d\n", numero);
return ;

}


