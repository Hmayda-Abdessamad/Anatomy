package com.example.modelisation3dducorpshumain.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AuthoritiesDeserializer extends StdDeserializer<List<GrantedAuthority>> {

    public AuthoritiesDeserializer() {
        super(List.class);
    }

    @Override
    public List<GrantedAuthority> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode authoritiesNode = rootNode.get("authorities");

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (authoritiesNode != null && authoritiesNode.isArray()) {
            Iterator<JsonNode> iterator = authoritiesNode.elements();
            while (iterator.hasNext()) {
                JsonNode authorityNode = iterator.next();
                String authorityValue = authorityNode.get("authority").asText();
                authorities.add(new SimpleGrantedAuthority(authorityValue));
            }
        }

        return authorities;
    }
}
