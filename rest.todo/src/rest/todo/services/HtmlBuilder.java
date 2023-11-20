package rest.todo.services;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlBuilder {
    public static void main(String[] args) {
        // Créer une instance de StringBuilder pour générer la page HTML
        StringBuilder htmlBuilder = new StringBuilder();

        // Ajouter les balises HTML, head et body
        htmlBuilder.append("<html><head><title>Page HTML générée avec StringBuilder</title></head><body>");

        // Ajouter du contenu dans la page HTML
        htmlBuilder.append("<h1>Bienvenue sur ma page HTML générée avec StringBuilder !</h1>");
        htmlBuilder.append("<p>Cette page a été générée dynamiquement en utilisant Java et StringBuilder.</p>");

        // Fermer les balises body et html
        htmlBuilder.append("</body></html>");

        // Écrire le contenu HTML généré dans un fichier
        try (FileWriter fileWriter = new FileWriter("index.html")) {
            fileWriter.write(htmlBuilder.toString());
            System.out.println("Le fichier HTML a été généré avec succès !");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la génération du fichier HTML : " + e.getMessage());
        }
    }
}
