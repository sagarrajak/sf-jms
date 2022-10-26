package spring.tutorial.smjms.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWordMessage implements Serializable {
    static final long serialVersionUID = 2_32323232323L;
    private UUID id;
    private String message;
}
