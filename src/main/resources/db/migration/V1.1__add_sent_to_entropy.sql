ALTER TABLE entropy ADD COLUMN sent BIT NULL DEFAULT 0;
UPDATE entropy SET sent = true;
