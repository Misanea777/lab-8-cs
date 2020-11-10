package DbAccess;
import Encryption.Encryptor;
import Encryption.Hasher;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DbControll {
    //        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
//        mongoLogger.setLevel(Level.SEVERE);


    private MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://admin:lohotron@cluster0.ux9po.mongodb.net/test?retryWrites=true&w=majority");

    private MongoClient mongoClient = new MongoClient(uri);
    private MongoDatabase database = mongoClient.getDatabase("Garus");

    public DbControll() {

    }

//    public void makeConnection() {
//        MongoClientURI uri = new MongoClientURI(
//                "mongodb+srv://admin:lohotron@cluster0.ux9po.mongodb.net/test?retryWrites=true&w=majority");
//
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("Garus");
//    }

    public void insertSomeData() {
        MongoCollection collection = database.getCollection("GarusC");
        Document document1 = new Document()
                .append("name",  Encryptor.encryptwithAES("Misa"))
                .append("age", "old")
                .append("email", Encryptor.encryptwithAES("someEmail@email.com"))
                .append("planet", "Earth");
        Document document2 = new Document()
                .append("name",  Encryptor.encryptwithAES("Grigore"))
                .append("age", "very old")
                .append("email",  Encryptor.encryptwithAES("grigores@email.com"))
                .append("planet", "Uranus");
        Document document3 = new Document()
                .append("name",  Encryptor.encryptwithAES("Yurii"))
                .append("age", "dying")
                .append("email",  Encryptor.encryptwithAES("otherEmail@email.com"))
                .append("planet", "Earth");

        collection.insertOne(document1);
        collection.insertOne(document2);
        collection.insertOne(document3);
    }

    public void insertNewUser(String username, String pswd, String email) {
        MongoCollection collection = database.getCollection("GarusC");
        Document user = new Document()
                .append("username",  Encryptor.encryptwithAES(username))
                .append("email",  Encryptor.encryptwithAES(email))
                .append("pswd",  Encryptor.encryptwithAES(Hasher.genHash(pswd)));
        collection.insertOne(user);
    }


    public void showInfo() {
        List<JSONObject> info = new ArrayList<JSONObject>();

        MongoCollection collection = database.getCollection("GarusC");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                info.add(new JSONObject(cursor.next().toJson()));

            }
        } finally {
            cursor.close();

            System.out.println("Encrypted data------------------");
            for (JSONObject o: info) {
                System.out.println("name : " +o.opt("name").toString());
                System.out.println("age : "+ o.opt("age").toString());
                System.out.println("email : " + o.opt("email").toString());
                System.out.println("planet : " + o.opt("planet").toString());
                System.out.println();
            }
        }
    }

    public void showDecrinfo() {
        List<JSONObject> info = new ArrayList<JSONObject>();

        MongoCollection collection = database.getCollection("GarusC");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                info.add(new JSONObject(cursor.next().toJson()));

            }
        } finally {
            cursor.close();

            System.out.println("Dencrypted data------------------");
            for (JSONObject o: info) {
                System.out.println("name : " + Encryptor.decryptAes(o.opt("name").toString()));
                System.out.println("age : "+ o.opt("age").toString());
                System.out.println("email : " + Encryptor.decryptAes(o.opt("email").toString()));
                System.out.println("planet : " + o.opt("planet").toString());
                System.out.println();
            }
        }
    }

    public void showUsers() {
        List<JSONObject> info = new ArrayList<JSONObject>();

        MongoCollection collection = database.getCollection("GarusC");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                info.add(new JSONObject(cursor.next().toJson()));

            }
        } finally {
            cursor.close();

            System.out.println("Users------------------");
            for (JSONObject o: info) {
                System.out.println("username : " + Encryptor.decryptAes(o.opt("username").toString()));
                System.out.println("email : " + Encryptor.decryptAes(o.opt("email").toString()));
                System.out.println("pswd : " + Encryptor.decryptAes(o.opt("pswd").toString()));
                System.out.println();
            }
        }
    }



}
