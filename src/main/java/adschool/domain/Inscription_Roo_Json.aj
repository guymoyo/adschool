// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Inscription;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Inscription_Roo_Json {
    
    public String Inscription.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Inscription Inscription.fromJsonToInscription(String json) {
        return new JSONDeserializer<Inscription>().use(null, Inscription.class).deserialize(json);
    }
    
    public static String Inscription.toJsonArray(Collection<Inscription> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Inscription> Inscription.fromJsonArrayToInscriptions(String json) {
        return new JSONDeserializer<List<Inscription>>().use(null, ArrayList.class).use("values", Inscription.class).deserialize(json);
    }
    
}