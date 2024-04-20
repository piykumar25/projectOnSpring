package com.codeWithPiyush.SpringBatchApp.config;

import com.codeWithPiyush.SpringBatchApp.entity.Customer;
import com.codeWithPiyush.SpringBatchApp.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class CsvBatchConfig {

    @Autowired
    CustomerRepository customerRepository;


    // create Reader
    @Bean
    public FlatFileItemReader<Customer> customerReader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    /**
     * Configures the line mapper for Customer objects.
     *
     * @return the configured line mapper
     */
    private LineMapper<Customer> lineMapper() {

        // Create a new DefaultLineMapper instance
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        // Configure the DelimitedLineTokenizer
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

        // Configure the BeanWrapperFieldSetMapper
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        // Set the configured lineTokenizer and fieldSetMapper to the lineMapper
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        // Return the configured lineMapper
        return lineMapper;
    }

    // Create Processor
    @Bean
    public CustomerProcessor customerProcessor() {
        return new CustomerProcessor();
    }


    // Create Writer
//    @Bean
//    public RepositoryItemWriter<Customer> customerWriter(){
//        RepositoryItemWriter<Customer> repositoryItemWriter = new RepositoryItemWriter<>();
//        repositoryItemWriter.setRepository(customerRepository);
//        repositoryItemWriter.setMethodName("save");
//        return repositoryItemWriter;
//    }

    @Bean
    public JdbcBatchItemWriter<Customer> jdbcBatchItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Customer> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO CUSTOMER_INFO VALUES (:id, :firstName, :lastName, :email, :gender, :contactNo, :country, :dob)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return jdbcBatchItemWriter;
    }


    // Create Step
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                     JdbcBatchItemWriter<Customer> jdbcBatchItemWriter) {
        int chunkSize = 10;
        return new StepBuilder("step-1", jobRepository)
                .<Customer, Customer>chunk(chunkSize, transactionManager)
                .reader(customerReader())
                .processor(customerProcessor())
                .writer(jdbcBatchItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    // Create Job
    @Bean
    public Job job(JobRepository jobRepository,
                   Step step) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }







}
