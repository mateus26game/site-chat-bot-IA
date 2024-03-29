package me.dio.sdw24.domain.model;

public record Champion(
        Long id,
        String name,
        String role,
        String lore,
        String imageUrL

) {
    public String generateContexByQuestion(String question){
        return """
                Pergunta: %s
                nome do Campeao: %s
                Função: %s
                Lore (Historia): %s
                """.formatted(question,this.name,this.role,this.lore);
    }

}
