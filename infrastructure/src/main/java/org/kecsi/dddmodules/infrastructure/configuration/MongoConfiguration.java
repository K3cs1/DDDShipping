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

//	private final List<Converter<?, ?>> converters = new ArrayList<>();

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

//	@Override
//	public Collection<String> getMappingBasePackages() {
//		return Collections.singleton( "com.baeldung" );
//	}

//	@Bean
//	public UserCascadeSaveMongoEventListener userCascadingMongoEventListener() {
//		return new UserCascadeSaveMongoEventListener();
//	}
//
//	@Bean
//	public CascadeSaveMongoEventListener cascadingMongoEventListener() {
//		return new CascadeSaveMongoEventListener();
//	}

//	@Override
//	public MongoCustomConversions customConversions() {
//		converters.add( new UserWriterConverter() );
//		return new MongoCustomConversions( converters );
//	}

	@Bean
	MongoTransactionManager transactionManager( MongoDatabaseFactory dbFactory ) {
		return new MongoTransactionManager( dbFactory );
	}

	@Override
	protected boolean autoIndexCreation() {
		return true;
	}
}
