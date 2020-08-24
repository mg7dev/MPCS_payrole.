import React from 'react';
import { Container, Row, Col, Button,Input,Form,FormGroup } from 'reactstrap';

const Home = (props) => {
  return (
    <Container style={{paddingTop:'10vh',background:'#102454'}}>
      <Row style={{textAlign:'center'}}>
        <Col>
            <h2>Welcome to Home</h2>
        </Col>
      </Row>
      <Row style={{textAlign:'center',padding:'20vh'}}>
          <Col>
                <Button>Add Employee</Button>
          </Col>
          <Col>
                <Button>View Employees</Button>
          </Col>
      </Row>
     
    </Container>
  );
}

export default Home;