package greboreda.graphqlexample.api;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

public class GraphQLConfig {

	@Autowired
	private GraphQLSchema graphQLSchema;

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		final ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
		final GraphQLServlet servlet = new SimpleGraphQLServlet(graphQLSchema, executionStrategy);
		return new ServletRegistrationBean<>(servlet, "/graphql");
	}

}
