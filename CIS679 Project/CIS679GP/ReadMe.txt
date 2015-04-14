To Run
1) RunFlesh.java -- Runs Flesh on the text files & saves all output to one joint file
2) ParseResults.java -- Parses the Flesh results file into a list of Flesch readability scores
3) EvaluateResults.java -- Assigns a more human-readable score to the Flesch readability scores
4) EditModel.java -- Adds the human-readable scores to the metadata records
5) ConstructGBDataset.java -- Constructs the TDB-backed dataset using the edited metadata records

Other Files (Don't need to run these)
CopyFiles.java -- Copies GP metadata records to one joint folder
LogStreamReader.java -- Writes output from RunFlesh to file
ScanFiles.java -- Scans GP metadata records to identify foreign language titles

Other Folders
The Gutenberg folder contains three folders and one text file.
1) FleshResults -- The text files that contain the raw and parsed results from running Flesh against the Project Gutenberg text files.
2) Records -- The unedited and edited RDF descriptions of the Project Gutenberg text files.
3) Text -- The Project Gutenberg text files.