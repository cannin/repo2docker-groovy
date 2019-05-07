@GrabResolver(name='biopax', root='http://www.biopax.org/m2repo/releases/')
@Grab(group='org.sbgn', module='libsbgn', version='0.2')
@Grab(group='pathwaycommons', module='chilay-sbgn', version='3.0.0')
@Grab(group='org.biopax.paxtools', module='paxtools-core', version='5.1.0')
@Grab(group='org.biopax.paxtools', module='sbgn-converter', version='5.1.0')

import java.io.*;
import java.util.*;
import org.sbgn.bindings.Sbgn;
import org.biopax.paxtools.io.sbgn.SBGNLayoutManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

String inputFile = "BIOMD0000000010.sbgn";
String outputFile = "r10.sbgn"

// Necessary to read in SBGNML file using the libSBGN library
JAXBContext context = JAXBContext.newInstance("org.sbgn.bindings");
Unmarshaller unmarshaller = context.createUnmarshaller();
Marshaller marshaller = context.createMarshaller();
marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

// Read in file
File sbgnFile = new File(inputFile);
Sbgn result = (Sbgn)unmarshaller.unmarshal(sbgnFile);

// Layout patway
(new SBGNLayoutManager()).createLayout(result, true);

// Save to file
marshaller.marshal(result, new FileOutputStream(outputFile));