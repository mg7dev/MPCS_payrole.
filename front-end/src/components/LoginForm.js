import React from 'react';
import { Container, Row, Col, Button,Input,Form,FormGroup } from 'reactstrap';
import { useForm } from "react-hook-form";
const LoginForm = (props) => {
  const handleLoginClick = () => {

  }
  return (
    <Container style={{paddingTop:'30vh',background:'#102454'}}>
      <Row style={{textAlign:'center'}}>
        <Col>
            <h2>MPCS PAY ROLE</h2>
            <h4>we care your salaries.</h4>
            <Button>Click here!</Button>
        </Col>
        <Col>
            <Form action="http:/localhost:8080/authenticate" method="post">
                <h3>Please sign in</h3>
                <FormGroup>
                    <Input type="test" name="userName"  placeholder="username" />
                </FormGroup>
                <FormGroup>
                    <Input type="password" name="password"  placeholder="password" />
                </FormGroup>
                <FormGroup>
                    <Button onclick ={handleLoginClick}>login</Button>
                </FormGroup>
            </Form>
        </Col>
      </Row>
     
    </Container>
  );
}

export default LoginForm;