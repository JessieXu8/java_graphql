package com.howtographql.hackernews.repository;

import com.howtographql.hackernews.po.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author Ocean Liang
 * @date 2/26/2019
 */
public class UserRepository {
    private final MongoCollection<Document> users;

    public UserRepository(MongoCollection<Document> users) {
        this.users = users;
    }

    public User saveUser(User user) {
        Document doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        users.insertOne(doc);
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }

    private User user(Document doc) {
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }

    public User findByEmail(String email) {
        Document doc = users.find(eq("email", email)).first();
        return user(doc);
    }

    public User findById(String userId) {
        Document doc = users.find(eq("_id", new ObjectId(userId))).first();
        return user(doc);
    }
}
