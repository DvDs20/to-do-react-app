import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import styled from 'styled-components';

const FormContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  align-items: center;
`;

const TaskForm = ({ onAddTask }) => {
    const [newTaskTitle, setNewTaskTitle] = useState('');

    const handleAddTask = () => {
        if (newTaskTitle.trim() === '') return;
        onAddTask(newTaskTitle);
        setNewTaskTitle('');
    };

    return (
        <FormContainer>
            <Form.Control
                type="text"
                placeholder="Add a new task..."
                value={newTaskTitle}
                onChange={(e) => setNewTaskTitle(e.target.value)}
            />
            <Button style={{ width: 'inherit' }} onClick={handleAddTask}>
                Add Task
            </Button>
        </FormContainer>
    );
};

export default TaskForm;
