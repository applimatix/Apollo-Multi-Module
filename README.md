# Apollo-Multi-Module

This app explores the efficiencies that can be gained using multiple modules with Apollo in it's current state.

Specifically can fragments be shared across modules.

## Outcomes

Although not a perfect solution, it is possible to structure modules so that their graphql queries 
can be defined in the module and picked up and compiled into a central apollo module. This allows 
the models created by the queries to be centrally located and available for use by any module that 
lists the apollo module as a dependency. The fragments used by the modules can also be shared by 
being stored in the central apollo module  

## To run the app

1. Import the project into Android Studio
2. Install https://github.com/APIs-guru/graphql-faker
3. Run graphql faker using graphql-faker -o /.../ApolloMultiModule/apollo/faker/schema.faker.json
4. Build and Run the App which currently points to the server running on the localhost
