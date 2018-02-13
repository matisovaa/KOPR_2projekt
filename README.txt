KOPR 2. projekt 1. zadanie (od kodu k WSDL)
--------------------------------------------

Sklada sa z dvoch projektov jeden, ktory predstavuje server (KOPR_2projekt) a druhy, ktory predstavuje klienta (KOPR_2projekt_klient).

Teraz je to vo Faktory.java na servery nastavene tak, ze service bezi nad testovacou databazou (aby fungovali testy klienta), ktora nema tabulky, lebo tie sa vytvaraju a mazu pri kazdom teste. Ale v spominanej Faktory.java je zakomentovane pripajanie sa na normalnu databazu, nad ktorou moze service bezat v normalnom rezime, ale v tomto rezime uz nebudu fungovat testy klienta. Skript na vytvorenie netestovacej databazy je v KOPR_2projekt/DB_evidencia_ucasti.sql

Oba projekty: 
	Java verzia: jre1.8.0_151 (jdk1.8.0_121)
	Vyuzivaju Maven
	Boli vytvorene v NetBeans IDE 8.2

Databaza:
	Vyuziva MySQL datab·zu

Unit testy:
	- Treba si vyrobiù testovaciu databazu spustenim sql skriptu, ktory sa nachadza v projekte KOPR_2projekt/DB_evidencia_ucasti_test.sql . Tento skript vytvori sam aj pouzivatela, ktory ma opravnenia na testovaciu databazu.
	- Pre testy klienta treba spustit server KOPR_2projekt/src/main/java/server/Server.java
	- Samotne testy sa nachadzaju v oboch projektoch v adresari src/test/java, v projekte klienta je triedov testu len trieda KlientTest.java
	











