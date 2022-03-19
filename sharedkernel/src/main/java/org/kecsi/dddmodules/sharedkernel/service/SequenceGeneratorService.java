package org.kecsi.dddmodules.sharedkernel.service;

import java.util.Objects;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.model.DatabaseSequence;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
@Service
public class SequenceGeneratorService {

	private MongoOperations mongoOperations;

	public long generateSequence( String sequenceName ) {
		DatabaseSequence counter = mongoOperations.findAndModify( query( where( "_id" ).is( sequenceName ) ),
				new Update().inc( "seq", 1 ), options().returnNew( true ).upsert( true ),
				DatabaseSequence.class );
		return !Objects.isNull( counter ) ? counter.getSeq() : 1;
	}
}
