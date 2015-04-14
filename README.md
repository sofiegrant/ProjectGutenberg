# ProjectGutenberg
A project that adds readability scores to Project Gutenberg RDF files.

This project was completed by Sofie Grant and Alexandros Plakidas Ntasios (plakidaa@mail.gvsu.edu) in conjunction with Grand Valley State University's CIS 679.

The project contains the following:

1) CIS679GB -- Our triple store database of Project Gutenberg sample titles.
2) CIS679GP -- Our Java files used to edit Project Gutenberg RDF records and insert them into the triple store database.
3) 679 Paper - Final -- Our paper regarding the project.

The project requires the following:

1) Apache Jena -- A triplestore database (https://jena.apache.org/download/index.cgi -- we used release 2.12.0)
2) Commons Beanutils -- Additional Java methods for array manipulation (http://commons.apache.org/proper/commons-beanutils/ -- we used release 1.9.2)
3) Commons Lang -- Additional Java methods for String manipulation (http://commons.apache.org/proper/commons-lang/ -- we used 3.3.2)
4) Flesh -- A Java application that provides text readability scores (http://flesh.sourceforge.net/ -- we used version 2.0)

Important Note: The Java program we wrote for this project can only run the Flesh program on approximately five text files at a time. This could be improved upon.
