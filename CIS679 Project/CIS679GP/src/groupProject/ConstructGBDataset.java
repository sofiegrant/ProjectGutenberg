/*Sources: 
 * http://jena.apache.org/documentation/tdb/java_api.html#constructing-a-model-or-dataset
 * for the basic TDB dataset setup code
 * 
 * https://jena.apache.org/documentation/tdb/tdb_transactions.html#read-transactions
 * for the basic TDB read transaction/SPARQL query setup code
 *
 * http://stackoverflow.com/questions/20365204/configure-eclipse-for-log4j
 * for addressing a common logging error
 * 
 * http://www.linkeddatatools.com/querying-semantic-data
 * for learning to write SPARQL queries
 * 
 * http://www.ibm.com/developerworks/library/j-sparql/
 * for reminding me to close my query after my transaction is complete
 * 
 * http://stackoverflow.com/questions/24783486/how-i-can-query-a-model-on-jena-tdb-given-a-name
 * for pointing me to FileManager.get().readModel(); when I was having difficulty figuring
 * out how to load my model into the dataset and reminding me to close my model after 
 * my transaction is complete
 * 
 * https://jena.apache.org/documentation/io/rdf-input.html
 * for pointing me to RDFDataMgr, which superceeds FileManager;
 * 
 * Program Purpose:
 * Stores PG metadata records to persistent storage
*/

package groupProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class ConstructGBDataset {
	static String modelBase = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/Records";
	static String myGBDirectory = "C:/Users/Senna/TDB/CIS679GB";
	static String gsparqlQuery1 = "PREFIX dcterms: <http://purl.org/dc/terms/> SELECT * WHERE { ?description dcterms:isFormatOf ?isFormatOf }";
	static String gSparqlQuery2 = "PREFIX dcterms: <http://purl.org/dc/terms/> SELECT * WHERE { ?description dcterms:audience ?audience }";
	
	public static void main(String[] args) throws IOException {
		String foreignFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/skipText.txt";
		String foreignCheckString = null;
		String [] foreignCheckArray = null;
		int [] foreignIntArray = null;
		
		// Create or locate TDB-backed dataset at the given location
		Dataset gbDataset = TDBFactory.createDataset(myGBDirectory);
		// Create a fresh empty model
		Model gbModel = gbDataset.getDefaultModel();
		
		// Open our list of foreign language titles
	    try
	    { 
	    	BufferedReader buff = new BufferedReader(new FileReader(foreignFile));
	        try {
	            StringBuilder sb3 = new StringBuilder();
	            String line = buff.readLine();

	            while (line != null) {
	                sb3.append(line);
	                line = buff.readLine();
	            }
	            foreignCheckString = sb3.toString();
	        } finally {
	        	buff.close();
	        }
	    } finally {
	    	foreignCheckArray = StringUtils.split(foreignCheckString, ",", 0);
	    	foreignIntArray = (int[])ConvertUtils.convert(foreignCheckArray, Integer.TYPE);
	    }
		
		
		for (int q = 1; q < 501; q++) {
			for (int r = 0; r < foreignIntArray.length; r++) {
				//If the book record index matches a foreign language book
				//upload the original file
				if (q == foreignIntArray[r]) {
						StringBuilder sb7 = new StringBuilder();
						sb7.append(modelBase);
						sb7.append("/pg");
						sb7.append(q);
						sb7.append(".rdf");
						String modelSet = sb7.toString();
						// Load my file into the model
						RDFDataMgr.read(gbModel, modelSet);
					} else {
						//If the book record index matches an English language book
						//upload the edited file
						StringBuilder sb8 = new StringBuilder();
						sb8.append(modelBase);
						sb8.append("/pg");
						sb8.append(q);
						sb8.append("Edited");
						sb8.append(".rdf");
						String modelSet = sb8.toString();
			
						// Load my file into the model
						RDFDataMgr.read(gbModel, modelSet);
					}
				}
		}

		// This query verifies that all records (foreign & English) are in the dataset
		gbDataset.begin(ReadWrite.READ);
		try (QueryExecution qExec1 = QueryExecutionFactory.create(gsparqlQuery1, gbDataset)){
			ResultSet rs1 = qExec1.execSelect();
			ResultSetFormatter.out(rs1);
			qExec1.close();
		} finally {
			gbModel.close();
			gbDataset.end();
		}
		
		// This query returns all books that have an audience tag (should exclude foreign language titles)
		gbDataset.begin(ReadWrite.READ);
		try (QueryExecution qExec2 = QueryExecutionFactory.create(gSparqlQuery2, gbDataset)){
			ResultSet rs2 = qExec2.execSelect();
			ResultSetFormatter.out(rs2);
			qExec2.close();
		} finally {
			gbModel.close();
			gbDataset.end();
		}
	}
}
