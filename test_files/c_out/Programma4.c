#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

char*  concatenaStringhe(  char str[],char str2[]);
void main ();
char str[1000] ={  "Ciao sono una stringa"  };
char*  concatenaStringhe(  char str[],char str2[]){
 return strcat( str, str2) ;

}





void main (){
char stt[1000] ;char temp[1000] ={  "stringa qualsiasi"  };  strcpy(stt,  concatenaStringhe( str, temp)) ;
printf("%s", stt);
return ;

}


