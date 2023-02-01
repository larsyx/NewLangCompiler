#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

int  scegliOpzione();
float  quadrato();
float  rettangolo();
float  triangolo();
float  rombo();
float  cerchio();
float  trapezio();
void main ();



int  scegliOpzione(){
int   scegli;
 printf(" questo programma calcola l'area di diverse forme geometriche\n");
printf("scegli tra le seguenti opzioni\n");
printf("\t0 quadrato\n");
printf("\t1 rettangolo\n");
printf("\t2 triangolo\n");
printf("\t3 rombo\n");
printf("\t4 cerchio\n");
printf("\t5 trapezio\n");
scanf("%d",&scegli);
 if(    scegli<= 5  &&  scegli>= 0    ) {
 return  scegli;

}
else {
 printf("input errato\n\n");

}
 
return - 1  ;

}


float  quadrato(){
float   l;
 printf("hai scelto quadrato\n");
printf("inserisci lunghezza lato: ");
scanf("%f",&l);
 l=pow( l, 2 ) ;
return  l;

}


float  rettangolo(){
float   h, l;
 printf("hai scelto rettangolo\n");
printf("inserisci lunghezza lato: \n");
scanf("%f",&l);
printf("inserisci lunghezza altezza: \n");
scanf("%f",&h);
return   l* h ;

}


float  triangolo(){
float   h, l;
 printf("hai scelto triangolo\n");
printf("inserisci lunghezza lato: \n");
scanf("%f",&l);
printf("inserisci lunghezza altezza: \n");
scanf("%f",&h);
return    l* h / 2  ;

}


float  rombo(){
float   d2, d1;
 printf("hai scelto rombo\n");
printf("inserisci diagonale minore: \n");
scanf("%f",&d1);
printf("inserisci diagonale maggiore: \n");
scanf("%f",&d2);
return    d1* d2 / 2  ;

}


float  cerchio(){
float   r;
 printf("hai scelto cerchio\n");
printf("inserisci raggio: \n");
scanf("%f",&r);
return  pow( r, 2 ) * 3.14  ;

}


float  trapezio(){
float   h, b2, b1;
 printf("hai scelto trapezio\n");
printf("inserisci base minore: \n");
scanf("%f",&b1);
printf("inserisci base maggiore: \n");
scanf("%f",&b2);
printf("inserisci altezza: \n");
scanf("%f",&h);
return     b2- b1 * h / 2  ;

}



void main (){
int   opz =   scegliOpzione();
float   risultato =  0.0 ;
  if(   opz != - 1   ) {
  if(   opz== 0   ) {
  risultato=  quadrato();

}
else {
  if(   opz== 1   ) {
  risultato=  rettangolo();

}
else {
  if(   opz== 2   ) {
  risultato=  triangolo();

}
else {
  if(   opz== 3   ) {
  risultato=  rombo();

}
else {
  if(   opz== 4   ) {
  risultato=  cerchio();

}
else {
  if(   opz== 5   ) {
  risultato=  trapezio();

}
 

}
 

}
 

}
 

}
 

}
 

}
 
printf("il risultato e': %f\n", risultato);
return ;

}


