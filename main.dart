import 'dart:io';
import 'dart:convert';
void main() {
var a;
var teste;
var b;
var c;
var numero;
var t;
a = stdin.readLineSync(encoding: utf8);
b = stdin.readLineSync(encoding: utf8);
numero = stdin.readLineSync(encoding: utf8);
b = 90;
print("Teste de escrita");
print(0);
if (a==0) {
b = 1;}else {
b = 100;}

if (numero==0) {
b = 1;}else {
b = 100;}

while (b<0) {
print(a);b = a-5;
}

t = "string";
switch (t) {
case "error": {
t = stdin.readLineSync(encoding: utf8);break;
}case "hello": {
print("world");break;
}default:
print(t);
break;
}

a = 1;
b = 1;
numero = 2.543;
t = "123";
a = stdin.readLineSync(encoding: utf8);
b = stdin.readLineSync(encoding: utf8);
print(b);
numero = (a*2.5)/(5.12*b);
a = b*2;
}