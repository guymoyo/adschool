// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.domain;

import adschool.domain.Eleve;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Eleve_Roo_Json {
    
    public String Eleve.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Eleve Eleve.fromJsonToEleve(String json) {
        return new JSONDeserializer<Eleve>().use(null, Eleve.class).deserialize(json);
    }
    
    public static String Eleve.toJsonArray(Collection<Eleve> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Eleve> Eleve.fromJsonArrayToEleves(String json) {
        return new JSONDeserializer<List<Eleve>>().use(null, ArrayList.class).use("values", Eleve.class).deserialize(json);
    }
    
}
