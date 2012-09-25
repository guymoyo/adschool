package adschool.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.RooConversionService;

import adschool.domain.AnneeScolaire;
import adschool.domain.DocInscription;
import adschool.domain.Document;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Inscription;
import adschool.domain.PensionEleves;


/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		
		registry.addConverter(new AnneeScolaireConverter());
		registry.addConverter(new PensionElevesConverter());
		registry.addConverter(new InscriptionConverter());
		registry.addConverter(new EtablissementConverter());
		registry.addConverter(new EleveConverter());
		registry.addConverter(new DocInscriptionConverter());
		registry.addConverter(new DocumentConverter());
		// Register application converters and formatters
		
		
	}
	
	 static class EleveConverter implements Converter<Eleve, String> {
	        public String convert(Eleve eleve) {
	            return new StringBuilder().append(eleve.getMatricule()).append(" - ").append(eleve.getNom()).append(" ").append(eleve.getPrenom()).toString();
	        }
	        
	    }

	
	 static class AnneeScolaireConverter implements Converter<AnneeScolaire, String> {
	        public String convert(AnneeScolaire anneeScolaire) {
	            return new StringBuilder().append(anneeScolaire.getLibelle()).toString();
	        }
	        
	    }
	 
	 static class InscriptionConverter implements Converter<Inscription, String> {
	        public String convert(Inscription inscription) {
	        	
	        	
	        	
	        	return new StringBuilder().append(inscription.getInscriptionKey()+" - "+inscription.getEleve().toString()).toString();
	        }
	        
	    }
	 

	 
	  static class PensionElevesConverter implements Converter<PensionEleves, String> {
	        public String convert(PensionEleves pensionEleves) {
	        	
	        	StringBuilder sb = new StringBuilder();
		        sb.append(pensionEleves.getPensionKey()).append(" - ");
		        sb.append(pensionEleves.getMontantPension()).append(" - ");
		        sb.append(pensionEleves.getTranche()).append(" - ");
		        sb.append(pensionEleves.getEleve());
		        
		        return sb.toString();
	            
	        }
	        
	    }
	  
	  static class EtablissementConverter implements Converter<Etablissement, String> {
	        public String convert(Etablissement etablissement) {
	            return new StringBuilder().append(etablissement.getNomEtablissement()).toString();
	        }
	        
	    }
	  
	  static class DocInscriptionConverter implements Converter<DocInscription, String> {
	        public String convert(DocInscription docInscription) {
	            return new StringBuilder().append(docInscription.getDocKey()).toString();
	        }
	        
	    }
	  
	  
	    static class DocumentConverter implements Converter<Document, String> {
	        public String convert(Document document) {
	            return new StringBuilder().append(document.getLibelle()).toString();
	        }
	        
	    }
}
