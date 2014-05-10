package loon.core.graphics.component;

import loon.core.LRelease;


public final class ActorNode implements LRelease {

	private Actor actor;

	private BSPCollisionNode node;

	private ActorNode next;

	private ActorNode prev;

	private boolean mark;

	public ActorNode(Actor actor, BSPCollisionNode node) {
		this.actor = actor;
		this.node = node;
		ActorNode first = BSPCollisionChecker.getNodeForActor(actor);
		this.next = first;
		BSPCollisionChecker.setNodeForActor(actor, this);
		if (this.next != null) {
			this.next.prev = this;
		}

		this.mark = true;
	}

	public void clearMark() {
		this.mark = false;
	}

	public void mark() {
		this.mark = true;
	}

	public boolean checkMark() {
		boolean markVal = this.mark;
		this.mark = false;
		return markVal;
	}

	public Actor getActor() {
		return this.actor;
	}

	public BSPCollisionNode getBSPNode() {
		return this.node;
	}

	public ActorNode getNext() {
		return this.next;
	}

	public void remove() {
		this.removed();
		this.node.actorRemoved(this.actor);
	}

	public void removed() {
		if (this.prev == null) {
			BSPCollisionChecker.setNodeForActor(this.actor, this.next);
		} else {
			this.prev.next = this.next;
		}
		if (this.next != null) {
			this.next.prev = this.prev;
		}
	}

	@Override
	public void dispose() {
		if (node != null) {
			node = null;
		}
		if (next != null) {
			next.dispose();
			next = null;
		}
		if (prev != null) {
			prev.dispose();
			prev = null;
		}

	}
}
