async function getSchedules() {
    try {
        const response = await fetch('/api/schedules');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const contentType = response.headers.get('content-type');
        if (!contentType || !contentType.includes('application/json')) {
            throw new TypeError(`Expected JSON, but got ${contentType}`);
        }
        const schedules = await response.json();
        const schedulesList = document.getElementById('schedulesList');
        schedulesList.innerHTML = '';
        schedules.forEach(schedule => {
            const card = document.createElement('div');
            card.className = 'card';
            card.innerHTML = `
                <h3>${schedule.title}</h3>
                <p>${schedule.content}</p>
                <p><strong>Worker:</strong> ${schedule.worker}</p>
                <p><strong>ID:</strong> ${schedule.id}</p>
                <button onclick="openCommentModal(${schedule.id})">Add Comment</button>
                <button onclick="openViewCommentsModal(${schedule.id})">View Comments</button>
            `;
            schedulesList.appendChild(card);
        });
    } catch (error) {
        console.error('Failed to fetch schedules:', error);
    }
}

async function getScheduleById() {
    const id = document.getElementById('scheduleId').value;
    const response = await fetch(`/api/schedules/${id}`);
    const schedule = await response.json();
    document.getElementById('scheduleDetails').innerHTML = JSON.stringify(schedule, null, 2);
}

async function createSchedule() {
    const title = document.getElementById('createTitle').value;
    const content = document.getElementById('createContent').value;
    const worker = document.getElementById('createWorker').value;
    const password = document.getElementById('createPassword').value;

    const response = await fetch('/api/schedules', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, content, worker, password })
    });

    const result = await response.json();
    document.getElementById('createResult').innerHTML = JSON.stringify(result, null, 2);
    getSchedules();
}


async function updateSchedule() {
    const id = document.getElementById('updateId').value;
    const title = document.getElementById('updateTitle').value;
    const content = document.getElementById('updateContent').value;
    const worker = document.getElementById('updateWorker').value;
    const password = document.getElementById('updatePassword').value;

    const response = await fetch(`/api/schedules/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, content, worker, password })
    });

    const result = await response.json();
    document.getElementById('updateResult').innerHTML = JSON.stringify(result, null, 2);
    getSchedules();
}

async function deleteSchedule() {
    const id = document.getElementById('deleteId').value;
    const password = document.getElementById('deletePassword').value;

    const response = await fetch(`/api/schedules/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ password })
    });

    const result = await response.json();
    document.getElementById('deleteResult').innerHTML = JSON.stringify(result, null, 2);
    getSchedules();
}

function openCommentModal(scheduleId) {
    console.log('Opening comment modal for schedule ID:', scheduleId);
    document.getElementById('commentScheduleId').value = scheduleId;
    document.getElementById('commentModal').style.display = 'block';
}

function closeCommentModal() {
    document.getElementById('commentModal').style.display = 'none';
}

async function postComment() {
    const scheduleId = document.getElementById('commentScheduleId').value;
    const content = document.getElementById('commentContent').value;
    const userId = document.getElementById('commentUserId').value;

    const response = await fetch('/api/comment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ scheduleId, content, userId })
    });

    const message = await response.text();

    if (response.ok) {
        document.getElementById('commentResult').innerHTML = message;
        closeCommentModal();
        getSchedules(); // Refresh the schedules list to show the new comment
    } else {
        alert('Failed to post comment: ' + message);
    }
}


function openViewCommentsModal(scheduleId) {
    console.log('Opening view comments modal for schedule ID:', scheduleId);
    fetchComments(scheduleId);
    document.getElementById('viewCommentsScheduleId').value = scheduleId;
    document.getElementById('viewCommentsModal').style.display = 'block';
}

function closeViewCommentsModal() {
    document.getElementById('viewCommentsModal').style.display = 'none';
}

async function fetchComments(scheduleId) {
    const response = await fetch(`/api/comment/${scheduleId}`);
    const comments = await response.json();
    const commentsList = document.getElementById('commentsList');
    commentsList.innerHTML = '';
    comments.forEach(comment => {
        const commentItem = document.createElement('div');
        commentItem.className = 'comment-item';
        commentItem.innerHTML = `
            <p><strong>User:</strong> ${comment.userId}</p>
            <p>${comment.content}</p>
            <button onclick="openEditCommentModal(${comment.commentId}, '${comment.content}')">Edit</button>
            <button onclick="deleteComment(${comment.commentId}, ${scheduleId})">Delete</button>
        `;
        commentsList.appendChild(commentItem);
    });
}

function openEditCommentModal(commentId, content) {
    console.log('Opening edit comment modal for comment ID:', commentId);
    document.getElementById('editCommentId').value = commentId;
    document.getElementById('editCommentContent').value = content;
    document.getElementById('editCommentModal').style.display = 'block';
}

function closeEditCommentModal() {
    document.getElementById('editCommentModal').style.display = 'none';
}

async function updateComment() {
    const commentId = document.getElementById('editCommentId').value;
    const content = document.getElementById('editCommentContent').value;
    const scheduleId = document.getElementById('viewCommentsScheduleId').value;

    const response = await fetch(`/api/comment/${commentId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ commentId, scheduleId, content })
    });

    const message = await response.text();

    if (response.ok) {
        document.getElementById('editCommentResult').innerHTML = message;
        closeEditCommentModal();
        fetchComments(scheduleId);
    } else {
        alert('Failed to update comment: ' + message);
    }
}

async function deleteComment(commentId, scheduleId) {
    const response = await fetch(`/api/comment/${commentId}`, {
        method: 'DELETE',
    });

    const message = await response.text();

    if (response.ok) {
        alert(message);
        fetchComments(scheduleId);
    } else {
        alert('Failed to delete comment: ' + message);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    getSchedules();
});
