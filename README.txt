KOPR 2. projekt 1. zadanie (od kodu k WSDL)
--------------------------------------------

Sklada sa z dvoch projektov jeden, ktory predstavuje server (KOPR_2projekt) a druhy, ktory predstavuje klienta (KOPR_2projekt_klient).

Oba projekty: 
	Java verzia: jre1.8.0_151 a jdk1.8.0_121
	Vyuzivaju Maven
	Boli vytvorene v NetBeans IDE 8.2

Databaza:
	Vyuziva MySQL datab·zu

Testy:
	Treba si vyrobiù databazu spustenim sql skriptu, ktory sa nachadza v projekte KOPR_2projekt/DB_evidencia_ucasti_test.sql
	Pre testy klienta treba spustit server KOPR_2projekt/src/main/java/server/server.java
	Samotne testy sa nachadzaju v oboch projektoch v adresari src/test/java, v projekte klienta je triedov testu len trieda KlientTest.java
	











