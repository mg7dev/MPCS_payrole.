import React ,{useState}from 'react';
import { Container, Row, Col, Button,Input,Form,FormGroup ,Label,Modal, ModalBody} from 'reactstrap';
const ALLOWANCE = 1
const DEDUCTION = 2
const AddEmp = (props) => {
    const [modal, setModal] = useState(false);
    const [IsAllow,setAllow] = useState(true);
    const modalbody = (IsAllow)?(
        <Col lg={12}>
            <h3 style={{color:'black'}}>Add allowances</h3>
            <FormGroup>
                <Input type="text" name="empId" placeholder="Id" />
            </FormGroup>
            <FormGroup>
                <Input type="text" name="empId" placeholder="name" />
            </FormGroup>
            <FormGroup>
                <Input type="text" name="empId" placeholder="amount" />
            </FormGroup>
        </Col>
    ):(
        <Col lg={12}>
            <h3 style={{color:'black'}}>Add deductions</h3>
            <FormGroup>
                <Input type="text" name="empId" placeholder="Id" />
            </FormGroup>
            <FormGroup>
                <Input type="text" name="empId" placeholder="name" />
            </FormGroup>
            <FormGroup>
                <Input type="text" name="empId" placeholder="amount" />
            </FormGroup>
        </Col>
    );
    const toggle = (action) =>{
        if(action==ALLOWANCE){
           setAllow(true)
        }
        if(action==DEDUCTION){
            setAllow(false)
        }
        setModal(!modal);
    } 
        
    return (
        <Container style={{paddingTop:'10vh',background:'#102454'}}>
            <Form>
                <Row style={{textAlign:'center'}}>
                    <Col>
                        <h2>Employee on Board</h2>
                    </Col>
                </Row>
                <Row style={{paddingTop:'20vh'}}>
                    <Col lg={6}>
                        <FormGroup>
                            <Label for="exampleEmail">Employee Id</Label>
                            <Input type="text" name="empId" />
                        </FormGroup>
                    </Col>
                    <Col lg={6}>
                        <FormGroup>
                            <Label for="exampleEmail">Email</Label>
                            <Input type="email" name="email"/>
                        </FormGroup>
                    </Col>
                    <Col lg={6}>
                        <FormGroup>
                            <Label for="exampleEmail">first name</Label>
                            <Input type="text" name="fname"/>
                        </FormGroup>
                    </Col>
                    <Col lg={6}>
                        <FormGroup>
                            <Label for="exampleEmail">last name</Label>
                            <Input type="text" name="lname"/>
                        </FormGroup>
                    </Col>
                    <Col lg={12} style={{paddingBottom:'20px'}}>
                        <Label style={{paddingRight:'15vw'}}>Add allowances</Label>
                        <Button type="button" size='sm' onClick={()=>toggle(1)}>+</Button>
                    </Col>
                    <Col lg={12}>
                        <Label style={{paddingRight:'15vw'}}>Add deductions</Label>
                        <Button type="button" size='sm' onClick={()=>toggle(2)}>+</Button>
                    </Col>
                </Row>
            </Form>
            <Modal isOpen={modal} fade={false} toggle={toggle} >
                <ModalBody>
                    <Row>
                        {modalbody}         
                    </Row>
                </ModalBody>
            </Modal>
        </Container>
    );
}

export default AddEmp;