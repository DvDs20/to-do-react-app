import React from 'react';
import { Button, ListGroup } from 'react-bootstrap';
import styled from 'styled-components';

const StyledTaskItem = styled(ListGroup.Item)`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: ${({ task }) => (task.status === 1 ? '#FF6B6B' : '#66DE93')};
  color: white;
  font-weight: bold;
`;

const ButtonsContainer = styled.div`
  display: flex;
  gap: 8px;
`;

const Task = ({ task, onDelete, onSetTaskToDone }) => {
    const handleSetTaskToDone = () => {
        onSetTaskToDone(task.id);
    };

    const handleDeleteTask = () => {
        onDelete(task.id);
    };

    return (
        <StyledTaskItem task={task}>
            <span>{task.title}</span>
            <ButtonsContainer>
                {task.status === 1 && (
                    <Button variant="success" onClick={handleSetTaskToDone}>
                        Done
                    </Button>
                )}
                <Button variant="danger" onClick={handleDeleteTask}>
                    Delete
                </Button>
            </ButtonsContainer>
        </StyledTaskItem>
    );
};

export default Task;
