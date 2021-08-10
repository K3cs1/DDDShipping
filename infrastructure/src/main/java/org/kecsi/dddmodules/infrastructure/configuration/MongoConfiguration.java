package org.kecsi.dddmodules.infrastructure.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource( { "infrastructure.properties" } )
@EnableMongoRepositories( basePackages = { "org.kecsi.dddmodules.ordercontext.repository", "org.kecsi.dddmodules.shippingcontext.repository" } )
@NoArgsConstructor
public class MongoConfiguration extends AbstractMongoClientConfiguration {

	@Value( "${mongoDBConnection}" )
	private String mongoDBConnection;

	@Value( "${mongoDBName}" )
	private String mongoDBName;

	@Override
	protected String getDatabaseName() {
		return mongoDBName;
	}

	@Override
	public MongoClient mongoClient() {
		final ConnectionString connectionString = new ConnectionString( mongoDBConnection );
		final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString( connectionString )
				.build();
		return MongoClients.create( mongoClientSettings );
	}

	@Bean
	MongoTransactionManager transactionManager( MongoDatabaseFactory dbFactory ) {
		return new MongoTransactionManager( dbFactory );
	}

	@Override
	protected boolean autoIndexCreation() {
		return true;
	}
}
