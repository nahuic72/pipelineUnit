package tests.job.exampleJobs.whenConditions

import spock.lang.*
import testSupport.PipelineSpockTestBase

class WhenConditionsJobTestSpec extends PipelineSpockTestBase {

    def "whenConditions Jenkinsfile test"() {

        when:
        runScript('exampleJobs/whenConditions/Jenkinsfile')

        then:
        printCallStack()
        assertJobStatusSuccess()

    }

    @Unroll
    def 'when branch is #name'() {

        given:
        binding.setVariable('BRANCH_NAME', name)

        when:
        runScript('exampleJobs/whenConditions/Jenkinsfile')

        then:
        printCallStack()
        assertJobStatusSuccess()

        then:
        testNonRegression("when_branch_is_${name}")

        where:
        name        | _
        'master'    | _
        'notmaster' | _

    }

    @Unroll
    def 'when running on #scenario'() {

        given:
        binding.setVariable('TAG_NAME', tag)

        when:
        runScript('exampleJobs/whenConditions/Jenkinsfile')

        then:
        printCallStack()
        assertJobStatusSuccess()

        then:
        testNonRegression("when_on_${scenario.replaceAll(' ', '_')}")

        where:
        tag             | scenario
        'this-is-a-tag' | 'a tag'
        'release-1.0.0' | 'a specific tag'

    }

}
