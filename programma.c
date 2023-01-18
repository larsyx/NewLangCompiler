#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

int  massimoComuneDivisore(  int n1,int n2, int*result);
int  minimoComuneMultiplo(  int n1,int n2, int*result);
void main ();

int  massimoComuneDivisore(  int n1,int n2, int*result){
int   resto;
  while(  n2> 0  ){
  resto=  n1/ n2 ;
 resto=  n1-  n2* resto  ;
 n1= n2;
 n2= resto;

}
 
 *result= n1;
return  n1;

}


int  minimoComuneMultiplo(  int n1,int n2, int*result){
int   ris =    n1* n2 /  massimoComuneDivisore( n1, n2,result);  ;
  *result= ris;
return  ris;

}





void main (){
int   n2, n1, tot;
 printf("inserisci 2 numeri per calcolare mcd e mcm\n");
printf("inserisci: ");
scanf("%d %d",&n1,&n2);
  massimoComuneDivisore( n1, n2,& tot); 
printf("stampo massimo comune divisore risultato: %d\n", tot);
  minimoComuneMultiplo( n1, n2,& tot); 
printf("stampo minimo comune multiplo risultato: %d\n", tot);
return ;

}


