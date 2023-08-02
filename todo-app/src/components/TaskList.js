import React from 'react';
import { ListGroup } from 'react-bootstrap';
import Task from './Task';

const TaskList = ({ tasks, onDelete, onSetTaskToDone }) => {
    return (
        <ListGroup className="mt-4">
            {tasks.map((task) => (
                <Task
                    key={task.id}
                    task={task}
                    onDelete={onDelete}
                    onSetTaskToDone={onSetTaskToDone}
                />
            ))}
        </ListGroup>
    );
};

export default TaskList;
